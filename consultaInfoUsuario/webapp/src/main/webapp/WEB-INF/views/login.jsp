<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<form action="login" method="post" class="form" role="form">
		<div class="row">
			<!-- panel preview -->
			<div class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-body form-horizontal payment-form">
						<div class="form-group">
							<label for="user" class="col-md-3 control-label">User</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="user" name="user" maxlength="20" value="${pageContext.request.userPrincipal.name}">
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-3 control-label">Password</label>
							<div class="col-md-9">
								<input type="password" class="form-control" id="pass" name="pass" maxlength="20">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 text-right">
								<button type="submit" class="btn btn-primary btn-block">
									<span class="glyphicon glyphicon-plus"></span> Acceder
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>