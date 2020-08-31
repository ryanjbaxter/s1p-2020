package contracts.toys

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method GET()
		url '/toys/bestseller/month'
	}
	response {
		status OK()
		headers {
			contentType("application/json")
		}
		body([
				[
						id      : $(anyNumber()),
						name    : 'Test',
						category: 'TOYS'
				]
		])
		bodyMatchers {
			jsonPath('$.[0].name', byType())
			jsonPath('$.[*]', byType {
				minOccurrence(3)
			})
		}
	}


}