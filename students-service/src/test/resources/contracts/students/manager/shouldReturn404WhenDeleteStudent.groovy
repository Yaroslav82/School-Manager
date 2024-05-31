package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of deleting student.
""")
    request {
        method DELETE()
        url '/students/delete/-1'
    }
    response {
        status NOT_FOUND()
    }
}
