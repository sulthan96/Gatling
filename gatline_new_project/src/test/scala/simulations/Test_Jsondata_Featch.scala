package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder


class Test_Jsondata_Featch extends Simulation {
  // http protocal

  val httpconfig = http.baseUrl("https://gorest.co.in/")
    .header("Authorization", "Bearer 3c6f405411c42042e3a206be14e3f24b18f93f8177cf12773c862e3b4f3505a1")

  // Scenario

  val Sce = scenario("jetching Json response data ")
    .exec(http("fetching jsonresponse request")
    .get("public/v2/posts")
    .check(jsonPath("$[0].id").saveAs("id")))

    .exec(toActionBuilder(http("get specific user")
      .get("public/v2/posts/${id}")
      .check(jsonPath("$[0].user_id").is("6790073"))))
      //.check(jsonPath("$.[1].email").is("chaturvedi_annapurna@hoppe-funk.test"))))

    setUp(Sce.inject(atOnceUsers(1))).protocols(httpconfig)


}
