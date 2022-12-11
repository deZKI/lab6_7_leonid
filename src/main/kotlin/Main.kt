//https://habr.com/ru/company/usetech/blog/665046/
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.io.File

//https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.net-film.ru%2Ffootage-84900%2F&psig=AOvVaw0dCMM9weU9p403utP43WXt&ust=1670824051508000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCPjfyeLu8PsCFQAAAAAdAAAAABAi
class Arrival() {
    var airline: String? = null
    var flight: String? = null
    var time: String? = null
    var from: String? = null
    var latest: String? = null
    var remarks: String? = null

    constructor(airline: String, flight: String, time: String, from: String, latest: String, remarks: String) : this() {
        this.airline = airline
        this.flight = flight
        this.time = time
        this.from = from
        this.latest = latest
        this.remarks = remarks
    }
    override fun toString(): String {
        return "Arrival " +
                "airline=" + airline +
                ", flight='" + flight + '\'' +
                ", time='" + time + '\''+
                "from=" + from +
                ", latest='" + latest + '\'' +
                ", remarks'" + remarks + '\''
    }
}

//вместо рута использовал arraylist
//class Root(){
//    //https://stackoverflow.com/questions/49155318/deserializing-xml-with-list-using-jackson
//    @JacksonXmlProperty(localName = "Person")
//    @JacksonXmlCData
//    @JacksonXmlElementWrapper(useWrapping = false)
//    var persons: Array<Person>?=null
//    constructor(persons: Array<Person>):this()
//    {
//        this.persons=persons
//    }
// }
val arrival = Arrival(
    "Аэрофлот", "UN7388", "17:30", "Владивосток", "18:30", "Багаж выдан"
);
val arrival1 = Arrival(
    "S7", "UN1238", "16:30", "Москва", "18:30", "Задерживается"
);
val arrival2 = Arrival(
    "Северный ветер", "UN73128", "17:30", "Новгород", "18:30", "Багаж выдан"
);
val arrivals = arrayListOf<Arrival>(arrival, arrival1, arrival2)
fun main() {
    println("XML:")
    xml()
    println("========================================================================================")
    println("Json:")
    json()
}


fun xml() {
    val om = XmlMapper()
    println("Сериализация:")
    om.writeValue(
        File("/Users/kirill201/Documents/lab6-7_leonid/src/main/kotlin/serialize_xml.xml"),
        arrivals
    ); // преобразование в xml сериализация
    println("Десериализация:")
    val xml = (om.readValue(
        File("/Users/kirill201/Documents/lab6-7_leonid/src/main/kotlin/deserialize_xml.xml"),
        Array<Arrival>::class.java
    )); //десериализация

    for (i in xml) { //дессериализация
        println(i)
    }
}

//https://stackoverflow.com/questions/19333106/issue-with-parsing-the-content-from-json-file-with-jackson-message-jsonmappin
fun json() {
    val om = jsonMapper()
    om.writeValue(
        File("/Users/kirill201/Documents/lab6-7_leonid/src/main/kotlin/serialize_json.json"),
        arrivals
    );//сериализация
    val json = (om.readValue(
        File("/Users/kirill201/Documents/lab6-7_leonid/src/main/kotlin/deserialize_json.json"),
        Array<Arrival>::class.java
    )); //десериализация
    for (i in json) { //дессериализация
        println(i)
    }

}
