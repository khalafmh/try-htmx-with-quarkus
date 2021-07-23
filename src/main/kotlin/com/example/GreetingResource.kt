package com.example

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/")
class GreetingResource {

    @CheckedTemplate
    class Templates {
        companion object {
            @JvmStatic
            external fun hello(name: String): TemplateInstance
            @JvmStatic
            external fun htmx(): TemplateInstance
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("hello")
    fun hello() = "Hello RESTEasy"

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("hello-template")
    fun helloTemplate(@QueryParam("name") nameParam: String?): String =
        Templates.hello(nameParam ?: "Person").render()

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("hello-htmx")
    fun helloHtmx(): String = Templates.htmx().render()
}
