var url = "http://localhost:8080/PyMapWAR/";

window.addEventListener("load", function() {
	if (window.location.href.indexOf(url) > -1) {
		//
	}
	
	if (window.location.href.indexOf(url + "producto/agregar") > -1) { 
		var select_marca = document.getElementById("marca");
		var select_tipoProducto = document.getElementById("tipoProducto");
		
		select_marca.addEventListener("change", function() {
			var opcionesTipoProducto = document.querySelectorAll('.opcionTipoProducto');
			
			var idMarca = select_marca.value;
			
			fetch(url + 'Producto?asincrono=1&marca=' + idMarca, {
	            method: 'GET',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	        })
	        .then(function(response) {
	            return response.json(); 
	        })
	        .then(function(datos) {
	        	opcionesTipoProducto.forEach(opcion => {
	        		opcion.remove();
	        	});
	        	
	        	for (var i = 0; i < datos.length; i++) {
	        		var option = document.createElement("option");
	        		
	        		option.value = datos[i].id;
	        		option.innerHTML = datos[i].nombre;
	        		option.classList.add("opcionTipoProducto");
	        		select_tipoProducto.appendChild(option);
				}
	        })
	        .catch(function(error) {
	        	console.log(error);
	        });
		});
	}
	
	if (window.location.href.indexOf(url + "negocio/administrar") > -1) {
		var urlConParametros = new URLSearchParams(window.location.search);
		var seleccion = urlConParametros.get("seleccion");
		
		if (seleccion == 2) {
			var rutNegocio = urlConParametros.get("negocio");
			
			function botonVerde() {
				$(".boton-verde").unbind("click").click(function() {
					var elemento = $(this);
					
					var idProducto = elemento.attr("data-idProducto");
					
					var input_precio = $("#" + idProducto);
					var precio = input_precio.val();
					
					fetch(url + 'ProductoNegocio?opcion=1&precio=' + precio + '&producto=' + idProducto + '&negocio=' + rutNegocio, {
			            method: 'GET',
			            headers: {
			                'Content-Type': 'application/json'
			            },
			        })
			        .then(function(response) {
			            return response.json(); 
			        })
			        .then(function(datos) {
			        	console.log(datos);
			        	
			        	if (datos.mensaje == "SERVIDOR: Producto agregado correctamente") {
			        		elemento.addClass("boton-rojo");
			        		elemento.addClass("btn-danger");
			        		elemento.removeClass("boton-verde");
			        		elemento.removeClass("btn-success");
			        		elemento.attr("data-idProductoNegocio", datos.idProductoNegocio);
			        		elemento.text("Eliminar");
			        		
			        		var botones = $("[" + "data-idProducto" + "=" + idProducto + "]");
			        		$.each(botones, function(i) {	  
			        			if (i == 1) {
			        				$(this).attr("data-idProductoNegocio", datos.idProductoNegocio);
			        				$(this).removeClass("disabled");
			        			}
			        		});
			        		
			        		if (precio > 0) {
			        			input_precio.addClass("text-success");
			        		}
			        		
			        		botonVerde();
			    			botonAmarillo();
			    			botonRojo();
			        	}
			        })
			        .catch(function(error) {
			        	console.log(error);
			        });
				});
			}
			
			function botonAmarillo() {
				$(".boton-amarillo").unbind("click").click(function() {
					var elemento = $(this);
					
					var idProducto = elemento.attr("data-idProducto");
					var idProductoNegocio = elemento.attr("data-idProductoNegocio");
					
					var input_precio = $("#" + idProducto);
					var precio = input_precio.val();
					
					fetch(url + 'ProductoNegocio?opcion=2&id=' + idProductoNegocio + '&precio=' + precio, {
			            method: 'GET',
			            headers: {
			                'Content-Type': 'application/json'
			            },
			        })
			        .then(function(response) {
			            return response.json(); 
			        })
			        .then(function(datos) {
			        	console.log(datos);
			        	
			        	if (datos.mensaje == "SERVIDOR: Producto modificado correctamente") {
			        		if (precio > 0) {
			        			input_precio.addClass("text-success");

			        		}
			        		if (precio == "") {
				        		input_precio.val("");
				        		input_precio.addClass("text-secondary");
				        		input_precio.removeClass("text-success");
			        		}
			        		
			        		botonVerde();
			    			botonAmarillo();
			    			botonRojo();
			        	}
			        })
			        .catch(function(error) {
			        	console.log(error);
			        });
				});
			}
			
			function botonRojo() {
				$(".boton-rojo").unbind("click").click(function() {
					var elemento = $(this);
					var idProductoNegocio = elemento.attr("data-idProductoNegocio");
					var idProducto = elemento.attr("data-idProducto");
					
					var input_precio = $("#" + idProducto);
					
					fetch(url + 'ProductoNegocio?opcion=3&productoNegocio=' + idProductoNegocio, {
			            method: 'GET',
			            headers: {
			                'Content-Type': 'application/json'
			            },
			        })
			        .then(function(response) {
			            return response.json(); 
			        })
			        .then(function(datos) {
			        	console.log(datos);
			        	
			        	if (datos.mensaje == "SERVIDOR: Producto eliminado correctamente") {
			        		elemento.addClass("boton-verde");
			        		elemento.addClass("btn-success");
			        		elemento.removeClass("boton-rojo");
			        		elemento.removeClass("btn-danger");
			        		elemento.removeAttr("data-idProductoNegocio");	
			        		elemento.text("Agregar");
			        		
			        		var botones = $("[" + "data-idProducto" + "=" + idProducto + "]");
			        		$.each(botones, function(i) {	  
			        			if (i == 1) {
			        				$(this).addClass("disabled");
			        			}
			        		});
			        		
			        		input_precio.val("");
			        		input_precio.addClass("text-secondary");
			        		input_precio.removeClass("text-success");
			        		
			        		botonVerde();
			    			botonAmarillo();
			    			botonRojo();
			        	}
			        })
			        .catch(function(error) {
			        	console.log(error);
			        });
				});
			}
			
			botonVerde();
			botonAmarillo();
			botonRojo();
		}
	}
});