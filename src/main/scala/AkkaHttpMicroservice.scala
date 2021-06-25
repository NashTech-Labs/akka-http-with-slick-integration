import akka.actor
import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.knoldus.service.{DatabaseService, HotelService}
import akka.http.scaladsl.Http
import com.knoldus.routes.HotelRoutes

object AkkaHttpMicroservice extends App {
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()
  val logger = Logging(system, getClass)

  val hotelService = new HotelService(databaseService)
  val hotelRoutes = new HotelRoutes(hotelService)
  val routes = hotelRoutes.route
  Http().bindAndHandle(routes,"localhost",8081)



}