package com.fb

import akka.actor.ActorSystem
import com.github.vooolll.client.FacebookClient
import com.github.vooolll.domain.oauth.{FacebookAccessToken, FacebookToken}
import com.github.vooolll.domain.permission.FacebookPermissions.UserDataPermissions
import com.github.vooolll.domain.posts.FacebookPostId
import com.github.vooolll.domain.profile.FacebookUserId
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger

import scala.util.{Failure, Success}

object Runner extends App {
  val log = Logger("runner")

  implicit val system = ActorSystem()
  //  implicit val materializer = ActorMaterializer()
  //  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val conf = ConfigFactory.load()

  val facebookClient = FacebookClient()
  val urlWithCodeAsQueryParam = facebookClient.authUrl(Set(UserDataPermissions.Posts))
  val urlWithTokenAsQueryParam = facebookClient.authUrl(Set(UserDataPermissions.Posts), FacebookToken)
  log.info(urlWithTokenAsQueryParam)

  //    Await.result(response2, 15 seconds)

  //    system.terminate()
  val userId = FacebookUserId(conf.getString("facebook.userId"))

  //  facebookClient.userAccessToken(clientCode) onComplete {
  //    case Success(userAccessToken) => {
  //      implicit val token = userAccessToken
  //
  //      val feed = Await.result(facebookClient.feed(userId), 3.seconds)
  //      println("Feed: + " + feed)
  //      println("------------------")
  //
  //    }
  //    case Failure(e) => println(e.getMessage)
  //  }


  //  facebookClient.extendUserAccessToken(shortLivedAccessToken) onComplete {
  //    case Success(longLivedToken) => println(longLivedToken)
  //    case Failure(e) => println(e.getMessage)
  //  }
  implicit val token: FacebookAccessToken = FacebookClient.accessToken(conf.getString("facebook.eltoken"))
  //  facebookClient.feed(FacebookUserId(userId.value)) map(feed =>
  //    println(feed))

  val res = facebookClient.post(FacebookPostId(s"${userId.value}_10156795616322486"))

  res.onComplete {
    case Success(value) => print(value)
    case Failure(exception) => log.error(s"error was $exception")
  }


  //  implicit val token = FacebookClient.accessToken(tokenStringValue)
  //
  //  val user = Await.result(facebookClient.userProfile(userId), 3.seconds)
  //  println("User: " + user)
  //  println("------------------")

}

