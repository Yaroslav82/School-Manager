package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of updating homework by id.
""")
    request {
        method 'PUT'
        url '/homework/update/-1'
        body(file('bodies/homeworkPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status NOT_FOUND()
    }
}
