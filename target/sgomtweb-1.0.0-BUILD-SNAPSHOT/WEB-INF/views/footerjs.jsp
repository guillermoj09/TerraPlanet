	<!-- Mainly scripts -->
	<script src="resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>


	<script src="resources/inspinia_v2.9/FullVersion/js/popper.min.js"></script>


	<!-- Custom and plugin javascript -->
	<script src="resources/inspinia_v2.9/FullVersion/js/inspinia.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/pace/pace.min.js"></script>

 <!-- Password meter -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/pwstrength/pwstrength-bootstrap.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/pwstrength/zxcvbn.js"></script>
    
     <!-- Ladda boton load-->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/spin.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/ladda.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/ladda.jquery.min.js"></script>
    
    <script type="text/javascript">

    

//submenu
//https://bootstrapthemes.co/demo/resource/bootstrap-4-multi-dropdown-hover-navbar/
$( document ).ready( function () {
    $( '.dropdown-menu a.dropdown-toggle' ).on( 'click', function ( e ) {
        var $el = $( this );
        var $parent = $( this ).offsetParent( ".dropdown-menu" );
        if ( !$( this ).next().hasClass( 'show' ) ) {
            $( this ).parents( '.dropdown-menu' ).first().find( '.show' ).removeClass( "show" );
        }
        var $subMenu = $( this ).next( ".dropdown-menu" );
        $subMenu.toggleClass( 'show' );
        
        $( this ).parent( "li" ).toggleClass( 'show' );

        $( this ).parents( 'li.nav-item.dropdown.show' ).on( 'hidden.bs.dropdown', function ( e ) {
            $( '.dropdown-menu .show' ).removeClass( "show" );
        } );
        
         if ( !$parent.parent().hasClass( 'navbar-nav' ) ) {
            $el.next().css( { "top": $el[0].offsetTop, "left": $parent.outerWidth() - 4 } );
        }

        return false;
    } );
} );

    </script>