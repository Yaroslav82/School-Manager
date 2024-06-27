package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of getting timetable.
""")
    request {
        method 'GET'
        url '/timetable/get'
    }
    response {
        status OK()
        body(file('bodies/timetableAll.json'))
    }
}