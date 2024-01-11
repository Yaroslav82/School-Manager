package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of updating lesson by id.
""")
    request {
        method 'PUT'
        url '/timetable/update/-1'
        body(file('bodies/lessonPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status NOT_FOUND()
    }
}
