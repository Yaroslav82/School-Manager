package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of deleting lesson by id.
""")
    request {
        method 'DELETE'
        url '/timetable/delete/3'
    }
    response {
        status NO_CONTENT()
    }
}
