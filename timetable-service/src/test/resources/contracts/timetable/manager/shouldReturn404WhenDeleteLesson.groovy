package contracts.timetable.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of deleting lesson by id.
""")
    request {
        method 'DELETE'
        url '/timetable/delete/-1'
    }
    response {
        status NOT_FOUND()
    }
}
