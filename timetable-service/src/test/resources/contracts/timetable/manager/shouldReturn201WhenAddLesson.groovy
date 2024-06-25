package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of adding new lesson to the timetable.
""")
    request {
        method 'POST'
        url '/timetable/add'
        body(file('bodies/lessonPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status CREATED()
        body(file('bodies/lessonPositive.json'))
    }
}