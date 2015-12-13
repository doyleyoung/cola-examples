package com.github.bmsantos.cola.kotlin.handler

import com.beust.jcommander.JCommander
import com.github.bmsantos.cola.kotlin.handler.handlers.*
import com.github.bmsantos.cola.kotlin.handler.sql2omodel.Sql2oModel
import freemarker.cache.ClassTemplateLoader
import freemarker.template.Configuration
import org.h2.jdbcx.JdbcDataSource
import org.slf4j.LoggerFactory
import org.sql2o.Sql2o
import org.sql2o.converters.Converter
import org.sql2o.converters.UUIDConverter
import org.sql2o.quirks.NoQuirks
import spark.Route
import spark.Spark.*
import spark.template.freemarker.FreeMarkerEngine
import java.util.*

object BlogService {

    private val logger = loggerFor<BlogService>()

    fun main(args: Array<String>) {
        val options = CommandLineOptions()
        JCommander(options, *args)

        logger.trace("Options.debug = " + options.debug)
        logger.trace("Options.servicePort = " + options.servicePort)

        port(options.servicePort)

        val dataSource = JdbcDataSource()
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=create schema if not exists test\\;runscript from 'classpath:/sql/init.sql'")
        dataSource.user = "sa"
        dataSource.password = ""

        val converters = hashMapOf<Class<*>?, Converter<*>?>(UUID::class.java to UUIDConverter())
        val sql2o = Sql2o(dataSource, NoQuirks(converters))

        val model = Sql2oModel(sql2o);
        val freeMarkerEngine = FreeMarkerEngine();
        val freeMarkerConfiguration = Configuration();
        freeMarkerConfiguration.templateLoader = ClassTemplateLoader(BlogService::class.java, "/");
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

        post("/posts", PostsCreateHandler(model));

        get("/posts", PostsIndexHandler(model));

        get("/posts/:uuid", GetSinglePostHandler(model));

        put("/posts/:uuid", PostsEditHandler(model));

        delete("/posts/:uuid", PostsDeleteHandler(model));

        post("/posts/:uuid/comments", CommentsCreateHandler(model));

        get("/posts/:uuid/comments", CommentsListHandler(model));

        get("/alive", Route { request, response -> return@Route "OK" });
    }
}

inline fun <reified T : Any> loggerFor() = LoggerFactory.getLogger(T::class.java)

fun main(args: Array<String>) {
    BlogService.main(args)
}