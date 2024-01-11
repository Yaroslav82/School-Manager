package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of getting all students.
""")
    request {
        method 'GET'
        url '/students/get'
    }
    response {
        status OK()
        body(file('bodies/studentsList.json'))
    }
}
