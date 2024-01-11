package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of creating new student.
""")
    request {
        method 'POST'
        url '/students/add'
        body(file('bodies/studentPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status CREATED()
        body(file('bodies/studentPositive.json'))
    }
}
