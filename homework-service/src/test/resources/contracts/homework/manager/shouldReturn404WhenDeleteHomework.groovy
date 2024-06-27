package contracts.homework.manager

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
<<<<<<<< HEAD:students-service/src/test/resources/contracts/students/manager/shouldReturn404WhenDeleteStudent.groovy
Represents a negative scenario of deleting student.
""")
    request {
        method DELETE()
        url '/students/delete/-1'
========
Represents a negative scenario of deleting homework.
""")
    request {
        method 'DELETE'
        url '/homework/delete/-1'
>>>>>>>> origin/feature/microservice-for-homework:homework-service/src/test/resources/contracts/homework/manager/shouldReturn404WhenDeleteHomework.groovy
    }
    response {
        status NOT_FOUND()
    }
}
