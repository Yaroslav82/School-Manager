package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of adding homework.
""")
    request {
        method 'POST'
        url '/homework/add'
        body(file('bodies/homeworkNegative.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status BAD_REQUEST()
    }
}