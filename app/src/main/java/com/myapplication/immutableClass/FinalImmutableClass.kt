package com.myapplication.immutableClass





fun main() {
    var id = 50
    var name ="Vishnu"
    var map = HashMap<String,String>()
    map.put("key1","Value1")
    map.put("key2","Value2")
    map.put("key3","Value3")
    var finalImmutableClass = FinalImmutableClass(id, name, map).also {
        print(it.toString())
        id = 20
        name = "modified"
        map.put("3", "third")
        //print the values again
        //print the values again
        System.out.println("ce id after local variable change:" + it.getId())
        System.out.println("ce name after local variable change:" + it.getName())
        System.out.println("ce testMap after local variable change:" + it.getMap())

        val hmTest: HashMap<String, String> = it.getMap()
        hmTest["4"] = "new"

        System.out.println("ce testMap after changing variable from accessor methods:" + it.getMap())

    }

}


class FinalImmutableClass(id:Int,name:String,map:HashMap<String,String>) {
    private val id:Int
    private val name:String
    private val  map:HashMap<String,String>

    fun getId():Int = id
    fun getName():String = name
    fun getMap():HashMap<String,String> = map
    init {
        this.id = id;
        this.name= name
        this.map = HashMap()
        var setKey = map.keys
        var iterator = map.iterator()
        for (key in setKey){
            if(iterator.hasNext()){
                var  entry=iterator.next()
                this.map.put(entry.key,entry.value)
            }
        }
    }

    override fun toString(): String {

        var string = "id = $id\nname = $name\nmap = $map"
        return string
    }

}