package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of getting all homeworks.
""")
    request {
        method 'GET'
        url '/homework/get'
    }
    response {
        status OK()
        body(file('bodies/homeworkAll.json'))
    }
}
