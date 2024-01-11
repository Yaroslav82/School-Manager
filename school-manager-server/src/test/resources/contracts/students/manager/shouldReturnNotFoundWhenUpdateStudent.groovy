package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of updating student by id.
""")
    request {
        method 'PUT'
        url '/students/update/-1'
        body(file('bodies/studentPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status NOT_FOUND()
    }
}
