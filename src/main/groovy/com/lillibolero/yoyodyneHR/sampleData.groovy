/*

Sample data for Yoyodyne HR Demo

45 Yoyodyne Employees (Red Lectroids)

*/

package com.lillibolero.yoyodyneHR


import com.orientechnologies.orient.core.metadata.schema.OClass
import com.orientechnologies.orient.core.metadata.schema.OType
import com.orientechnologies.common.exception.*
import com.tinkerpop.blueprints.impls.orient.*

// Fake New Jersey Social Security Numbers
class FakeSSN {
    int current = 9300
    String socialStart = "152-34"
    String nextSSN() { "$socialStart-${++current}" }
}

class SampleData {
    static dbName = "yoyodyneHR"
    static host = "your.example.com"
    static String dbStr = "remote:$host/$dbName"
    static String user = "root"
    static String passwd = "yourrootpasswd"
    OrientGraphFactory factory
    def ssnGen = new FakeSSN()

// War of the Worlds Broadcast
    Date birth = new Date().parse('yyyy/MM/ss', '1938/10/31')

    def departments = [
            [id:1, name:'Management', description:"The management team oversees all aspects of Yoyodyne's operations"],
            [id:2, name:'Security', description:"Maintain and protect the bivouac's physical security"],
            [id:3, name:'Overthruster Acquisition', description:"Enforce intellectual property rights stolen by the traitorous Akita"],
            [id:4, name:'Thermal Pod Design Group', description:"Conceptualize and implement an enterprise-ready, best of breed thermal pod"],
            [id:5, name:'Bivouac Maintenance', description:"Maintain a clean and safe bivouac environment for all red Lectroids"],
            [id:6, name:'Troop Ship Design Group', description:"Conceptualize and implement a revolutionary troop ship that ensures the recapture and liberation of Planet 10"]
    ]


    def positions = [
            [id:1, works:[1], name:'Lord',description:'Big Boss'],
            [id:2, works:[2], name:'CSO',description:'Chief Security Officer. Senior management in charge of all security and keeping monkey boys out of the facility'],
            [id:3, works:[3,4,6], name:'VP',description:'Vice President in charge of a department.'],
            [id:4, works:[5], name:'COO',description:'Chief Operations Officer. Senior management in charge of diverse business operations'],
            [id:5, works:[2], name:'Security Officer I',description:'Perform security duties as directed by Security Officer II'],
            [id:6, works:[2], name:'Security Officer II',description:'Manage Security Officer I staff and perform security duties as directed by CSO'],
            [id:7, works:[4], name:'Thermal Pod Engineer I',description:'Design thermal pod systems under direction of Senior Thermal Pod Engineer'],
            [id:8, works:[4], name:'Thermal Pod Engineer II',description:'Design thermal pod systems under direction of Senior Thermal Pod Engineer'],
            [id:9, works:[4], name:'Senior Thermal Pod Engineer',description:'Design thermal pod systems and manage engineering staff'],
            [id:10, works:[5], name:'Bivouac Maintenance Engineer I',description:'Checking, repairing and servicing machinery, equipment, systems and infrastructures in the facility'],
            [id:11, works:[5], name:'Bivouac Maintenance Engineer II',description:'Checking, repairing and servicing machinery, equipment, systems and infrastructures in the facility'],
            [id:12, works:[6], name:'Troop Ship Engineer I',description:'Design troop ship systems under direction of Senior Troop Ship Engineer'],
            [id:13, works:[6], name:'Troop Ship Engineer II',description:'Design troop ship systems under direction of Senior Troop Ship Engineer'],
            [id:14, works:[6], name:'Senior Troop Ship Engineer',description:'Design troop ship systems and manage engineering staff'],
            [id:15, works:[3], name:'Overthruster Acquisition Specialist I',description:'Find and reacquire Lord Whorfin\'s overthruster' ],
            [id:16, works:[3], name:'Overthruster Acquisition Specialist II',description:'Find and reacquire Lord Whorfin\'s overthruster. May direct Acquisition Specialist I.'],
    ]

    int LAST_MANAGER = 4
    def knownEmployees = [
            [id: 1, last: 'Whorfin', reports: 1, works: 1, title: 1],
            [id: 2, last: 'Bigboote', reports: 1, works: 3, title: 3],
            [id: 3, last: 'Gomez', reports: 2, works: 3, title: 15],
            [id: 4, last: "O'Connor", reports: 2, works: 3, title: 16],
            [id: 5, last: 'Camp', reports: 1, works: 2, title: 2],
            [id: 6, last: 'Smallberries', reports: 5, works: 2, title: 5],
            [id: 7, last: 'Ya Ya', reports: 5, works: 2, title: 6],
            [id: 8, last: 'Barnett', reports: 1, works: 5, title: 4],
            [id: 9, last: 'Careful Walker', reports: 1, works: 4, title: 3],
            [id: 10, last: 'Cooper', reports: 1, works: 6, title: 3],
            [id: 11, last: 'Chief Crier'],
            [id: 12, last: 'Coyote'],
            [id: 13, last: 'Edwards'],
            [id: 14, last: 'Fat Eating'],
            [id: 15, last: 'Fish'],
            [id: 16, last: 'Fledgling'],
            [id: 17, last: 'Grim'],
            [id: 18, last: 'Guardian'],
            [id: 19, last: 'Icicle Boy'],
            [id: 20, last: 'Jones'],
            [id: 21, last: 'Joseph'],
            [id: 22, last: 'Kim Chi'],
            [id: 23, last: 'Lee'],
            [id: 24, last: 'Littlejohn'],
            [id: 25, last: 'Many Jars'],
            [id: 26, last: 'Milton'],
            [id: 27, last: 'Mud Head'],
            [id: 28, last: 'Nephew'],
            [id: 29, last: 'Nolan'],
            [id: 30, last: 'Omar'],
            [id: 31, last: 'Parrot'],
            [id: 32, last: 'Rajeesh'],
            [id: 33, last: 'Ready to Fly'],
            [id: 34, last: 'Repeat Dance'],
            [id: 35, last: 'Roberts'],
            [id: 36, last: 'Scott'],
            [id: 37, last: 'Shaw'],
            [id: 38, last: 'Starbird'],
            [id: 39, last: 'Take Cover'],
            [id: 40, last: 'Thorny Stick'],
            [id: 41, last: 'Turk'],
            [id: 42, last: 'Two Horns'],
            [id: 43, last: 'Web'],
            [id: 44, last: 'Wood'],
            [id: 45, last: 'Wright'],
    ]


    void createSchema() {
        OrientGraphNoTx graph = factory.getNoTx()
        try {
            ["Department", "Position"].each { it ->
                OClass vertex = graph.createVertexType("$it")
                vertex.createProperty('name', OType.STRING).setMandatory(true).setNotNull(true)
                vertex.createProperty("description", OType.STRING).setMandatory(true).setNotNull(true)
            }

            OClass emp = graph.createVertexType("Employee")
            emp.createProperty("First", OType.STRING).setMandatory(true).setNotNull(true)
            emp.createProperty("Last", OType.STRING).setMandatory(true).setNotNull(true)
            emp.createProperty("SSN", OType.STRING).setMandatory(true).setNotNull(true)
            emp.createProperty("Birth", OType.DATE).setMandatory(true).setNotNull(true)

            ['works_at', 'reports_to', 'job'].each { it ->
                OClass edge = graph.createEdgeType("$it")
                edge.createProperty("start", OType.DATE).setMandatory(true).setNotNull(true)
                edge.createProperty("end", OType.DATE)
            }

        }
        catch (OException e) {
            println(e.message)
        }
        finally {
            graph.shutdown()
        }
    }

    void createDepartments() {
        OrientGraph graph = factory.getTx()
        try {
            departments.each { it ->
                def vert = graph.addVertex("class:Department",["name":it.name, "description":it.description])
                it['vertex'] = vert
            }
        }
        catch (OException e) {
            println(e.message)
        }
        finally {
            graph.shutdown()
        }

    }

    void createPositions() {
        OrientGraph graph = factory.getTx()
        try {
            positions.each { it ->
                def vert = graph.addVertex("class:Position",["name":it.name, "description":it.description])
                it['vertex'] = vert
            }
        }
        catch (OException e) {
            println(e.message)
        }
        finally {
            graph.shutdown()
        }

    }


    OrientVertex createEmployee(graph, employee) {
        // They're all named John
        String firstName = 'John'
        return graph.addVertex("class:Employee",["Last":employee.last, "First":firstName, "Birth":birth, "SSN":ssnGen.nextSSN()])
    }


    void createEmployees() {

        def addEdge = {name, inVert, outVert ->
            inVert.addEdge(name, outVert, ["start":birth])
        }
        def random = new Random()
        OrientGraph graph = factory.getTx()
        try {
            def management = []
            knownEmployees.each { it ->

                it["vertex"] = createEmployee(graph, it)
                if (it.containsKey("reports") && it["reports"] != it["id"]) {
                    management << it
                }
            }

            //set edges
            knownEmployees.each { it ->
                if (it.containsKey("works")) {
                    def dept = departments.find { d -> d["id"] == it.works }
                    def position = positions.find {p -> p["id"] == it.title}
                    addEdge("works_at", it["vertex"], dept["vertex"])

                    if (it.reports != it.id) {   // Whorfin reports to no one
                        def report = knownEmployees.find {m -> m["id"] == it.reports}
                        addEdge("reports_to", it["vertex"], report["vertex"])

                    }
                    addEdge("job", it["vertex"], position["vertex"])
                }
                else { // assign jobs at random
                    def manager = management[random.nextInt(management.size())]
                    def dept = departments.find { d -> d["id"] == manager["works"] }
                    addEdge("works_at", it["vertex"], dept["vertex"])
                    addEdge("reports_to", it["vertex"], manager["vertex"])
                    def openPositions = positions.findAll { p-> (dept["id"] in p["works"]) && p["id"] > LAST_MANAGER }
                    def position = openPositions[random.nextInt(openPositions.size())]
                    addEdge("job", it["vertex"], position["vertex"])
                }
            }
        }
        catch (OException e) {
            println(e.message)
        }
        finally {
            graph.shutdown()
        }
    }


    void fillData() {
        createDepartments()
        println("Created departments")
        createPositions()
        println("Created position")
        createEmployees()
        println("Created employees")
    }


    SampleData() {
        factory = new OrientGraphFactory(dbStr,user,passwd).setupPool(1,10)
    }
}

def sample = new SampleData()
sample.createSchema()
println("Created Schema")
sample.fillData()
println("Created values")
