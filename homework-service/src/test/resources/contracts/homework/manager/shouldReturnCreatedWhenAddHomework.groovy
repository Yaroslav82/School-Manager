package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of adding new homework.
""")
    request {
        method 'POST'
        url '/homework/add'
        body(file('bodies/homeworkPositive.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status CREATED()
        body(file('bodies/homeworkPositive.json'))
    }
}