package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class TestPostAPIsimulation extends Simulation {
  //Http

  val httpconf = http.baseUrl("https://reqres.in")
                 .header("Accept", "application/json")
                 .header("contant/type", "application/json")

  //Scenario
  val sec = scenario("Add new user Simulation")
            .exec(http("Add new user request")
             .post("/api/users")
              .body(RawFileBody("./src/test/resources/Bodies/AddUserBody.json")).asJson
              .check(status is 201))
    .pause(3)

    .exec(http("get user request")
    .get("/api/users/2")
    .check(status is 200))

    .pause(3)

    .exec(http("list of user ")
    .get("/api/users?page=2")
    .check(status is 200))

  //setup
  setUp(sec.inject(atOnceUsers(30))).protocols(httpconf)
}
