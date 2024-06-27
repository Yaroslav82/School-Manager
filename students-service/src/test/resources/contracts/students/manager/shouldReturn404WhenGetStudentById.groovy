package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of getting student by id.
""")
    request {
        method 'GET'
        url '/students/get/-1'
    }
    response {
        status NOT_FOUND()
    }
}
