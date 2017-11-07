<%@ include file="staticPage/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${id}</div>
				<h1><spring:message code="label.edit" /></h1>

				<form:form modelAttribute="computerForm" id="addComputer" action="#" method="POST">
					<form:hidden path="id" value="${id}"/>
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message code="computer.name" /></label>
																<spring:message code="computer.name"
								var="namePlaceholder" /> <form:input path="name"
								type="text" class="form-control" id="computerName"
								name="computerName" placeholder="${namePlaceholder}"
								pattern="^[0-9a-zA-Z������������ -_]{1,60}$"
								value="${computer.getName()}" required="true"></form:input>
						</div>
						<div class="form-group">

							<label for="introduced"><spring:message
									code="computer.introduced" /></label>
							<spring:message code="form.introduced.placeholder"
								var="introducedPlaceholder" />
							<form:input path="introduced" type="date" class="form-control"
								id="introduced" name="introduced"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								placeholder="${introducedPlaceholder}"
								value="${computer.getIntroduced()}"></form:input>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message
									code="computer.discontinued" /></label>
							<spring:message code="form.discontinued.placeholder"
								var="discontinuedPlaceholder" />
							<form:input path="discontinued" type="date" class="form-control"
								id="discontinued" name="discontinued"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
								placeholder="${discontinuedPlaceholder}"
								value="${computer.getDiscontinued()}"></form:input>
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="computer.companyName" /></label> <form:select
							path="companyId"
								class="input-lg form-control" id="companyId" name="companyId">
								<option value="0"></option>
								<c:forEach items="${companies}" var="company">
									<option value="${company.getId()}"
										<c:if test="${company.getId() == computer.getCompanyId()}"> selected </c:if> >
			                                                	${company.getName()}
			                                                	</option>
                                                </c:forEach>
									</form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
							<a class="btn btn-primary" id="addComputer" href="#"
								onclick="submitValidate();"><spring:message code="label.edit" /></a> or <a
								href="
				   				<c:url value="/dashboard">
				   				</c:url>"
								class="btn btn-default"> <spring:message code="label.cancel" /> </a>
						</div>
                        <div id="error" style="color:red"></div>
                        <p style="${empty errors ? "color:green" : "color:red"}">
	                       	<c:forEach items="${errors }" var="e">
	                       		<c:out value="${e }"/><br/>
	                       	</c:forEach>
						</p>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>  
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addComputer.js"></script>

</body>
</html>
