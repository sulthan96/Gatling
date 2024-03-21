package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder

class LoopRequest extends Simulation {
  val httpconf = http.baseUrl("https://reqres.in")
    .header("contant-type", "application/json")

  def gettheListofUser(): ChainBuilder = {
    repeat(2){
      exec(http("Get list of user details")
        .get("/api/users?page=2")
      .check(status.in(200, 220)))

    }
  }

  def getauserdetail(): ChainBuilder = {
    repeat(1) {
      exec(http("Get a single user details")
        .get("/api/users/2"))
    }
  }

  def addUser(): ChainBuilder = {
    repeat(1) {
      exec(http("Add new user request")
        .post("/api/users")
        .body(RawFileBody("./src/test/resources/Bodies/AddUserBody.json")).asJson
        .check(status is 201))
    }
  }

      val sce = scenario("get multi api response")
        .exec(gettheListofUser())
        .pause(2)
        .exec(getauserdetail())
        .pause(4)
        .exec(addUser())

  setUp(sce.inject(atOnceUsers(2))).protocols(httpconf)

  }
