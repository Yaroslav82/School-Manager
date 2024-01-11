package contracts.students.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a negative scenario of creating new student.
""")
    request {
        method 'POST'
        url '/students/add'
        body(file('bodies/studentNegative.json'))
        headers {
            contentType('application/json')
        }
    }
    response {
        status UNSUPPORTED_MEDIA_TYPE()
    }
}
