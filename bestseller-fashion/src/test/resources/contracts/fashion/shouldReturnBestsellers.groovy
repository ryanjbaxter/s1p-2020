package contracts.fashion

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method GET()
		url '/fashion/bestseller'
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
						category: 'FASHION'
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