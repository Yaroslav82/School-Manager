package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of getting timetable with query parameter.
""")
    request {
        method 'GET'
        url '/timetable/get?group=EP-222'
    }
    response {
        status OK()
        body(file('bodies/timetableWithGroupParam.json'))
    }
}
