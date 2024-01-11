package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of adding new lesson to the timetable.
""")
    request {
        method 'POST'
        url '/timetable/add'
        body(file('bodies/lessonNegative.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status UNSUPPORTED_MEDIA_TYPE()
    }
}
