 <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">${b.modulo}</a>
                        </li>
                        <li class="breadcrumb-item  <c:if test="${not empty b.menu}">   active  </c:if>">
                            <strong>${b.menu}</strong>
                        </li>
                         <c:if test="${not empty b.submenu}">   
                        <li class="breadcrumb-item active">
                            <strong>${b.submenu}</strong>
                        </li>
                          </c:if>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
</div>