package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of getting homeworks with query parameter.
""")
    request {
        method 'GET'
        url '/homework/get?group=GM-122'
    }
    response {
        status OK()
        body(file('bodies/homeworksWithGroupParam.json'))
    }
}

