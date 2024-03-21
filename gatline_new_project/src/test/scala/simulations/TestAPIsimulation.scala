package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestAPIsimulation extends Simulation {

  // Http call
  val httpconf =  http.baseUrl("https://reqres.in")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  // Scenario
  val sce = scenario("get User")
    .exec(http("get User details")
      .get("/api/users/2")
      .check(status is 200))


// set up

  setUp(sce.inject(atOnceUsers(200))).protocols(httpconf)


}