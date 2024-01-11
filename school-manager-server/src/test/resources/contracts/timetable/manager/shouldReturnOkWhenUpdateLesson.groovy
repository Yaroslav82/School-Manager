package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of updating lesson by id.
""")
    request {
        method 'PUT'
        url '/timetable/update/3'
        body(file('bodies/lessonPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body(file('bodies/lessonPositive.json'))
    }
}
