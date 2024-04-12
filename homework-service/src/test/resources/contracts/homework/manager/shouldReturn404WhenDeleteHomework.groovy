package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of deleting homework.
""")
    request {
        method 'DELETE'
        url '/homework/delete/-1'
    }
    response {
        status NOT_FOUND()
    }
}
