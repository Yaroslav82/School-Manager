package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of deleting student.
""")
    request {
        method DELETE()
        url '/students/delete/1'
    }
    response {
        status NO_CONTENT()
    }
}