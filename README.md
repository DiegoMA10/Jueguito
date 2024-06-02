# Jueguito
Projecto DAM

La idea es hacer un juego en 2D en la cual tendra una zona principal que sera un pueblo y luego un escenario para las batallas.

En el mapa podre interactuar con distintas cosas como por ejemplo NPC,Curar a los personajes,entrar en casas , etc...

La batalla podras elegir las acciones que quieres hacer como magias , atacar, o utilizar objetos. (la idea es que en las batalla sea un equipo de 3).

Link al repositorio github : https://github.com/DiegoMA10/Jueguito

## Pantalla de inicio

Cuando ejecutamos la aplicación aparece la pantalla del título donde podemos comenzar una partida nueva o cargar una ya existente, también podremos salir de la aplicación.

![alt text](imagenes/titleScreen.png)


## Empezar partida

Cuando comenzamos partida o cargamos partida comenzaremos en un punto del mapa principal
y para resumir como dibujo el mapa basicamente es una matriz de numero donde cada numero tiene una imagen asociada y ese numero dibuja una imagen u otra, cada cuadrado rojo es un mosaico o una baldosa que puede tener colision o no.

![alt text](imagenes/mapa.png)

Tambien podremos cambiar de mapa cuando pasemos por una puerta de cualquier casa del pueblo basicamente el juego detecta si la hitbox del personaje toca el punto del centro de cada baldosa 
entonces yo puedo decirle que en x baldosa haga un evento, en este caso es una transicion al mapa interior segun por que puerta entre el evento teletransportara al personaje a un sitio u otro.

<video controls src="imagenes/transicionMapaInt.mp4" title="Title"></video>

- El mapa interior : 

![alt text](imagenes/mapaInterior.png)

# Menu del juego

En cualquier parte del mapa podemos presionar la tecla esc y nos llevará al menú de personajes, en el menú podemos consultar la vida de los personajes, el nivel, el tiempo que hemos gastado, el dinero que tenemos (G) , y también podremos acceder a submenús como items, stats, order y save (los únicos operativos), también esta exit que te llevará a la pantalla de inicio.

![alt text](imagenes/menu.png)

## Items

Dentro de Items tendremos basicamente el inventario con todos nuestros objetos con su cantidad y si tenemos el cursor encima nos aparecerá una pequeña descripción del objeto, tambien si pulsamos enter en el objeto con el cursor nos llevara a otra vista para poder elegir con que personaje queremos consumir el objeto.
 
 ![alt text](imagenes/items.png)

 <video controls src="imagenes/itemsUse.mp4" title="Title"></video>

 ## Stats

Cuando pulsemos podremos elegir cual de los personajes queremos inspecciónar y cuando eligamos cualquiera de ellos nos llevará a su ventana de estado donde podremos ver basicamente todas sus estadisticas , el nivel , la experiencia que tiene y la que necesita para subir de nivel.
 
 ![alt text](imagenes/stats.png)

 ## Order

 Tambien tenemos el menu de order esto no influye nada en la jugabilidad pero basicamente es para que en el mapa principal puedas manejar un personaje u otro.
 
 <video controls src="imagenes/order.mp4" title="Title"></video>


 ## Save 

Para el save utilizamos una base de datos para poder guardar las partidas del juego , podemos guardar un total de 3 partidas distintas una vez llenas los 3 espacios de guardado solo se podra sobrescribir en esos mismos espacios por lo que solo se puede tener 3 partidas distintas.

Cada partida guardara el estado de nuestros personajes como el nivel, la vida , la mp , su experiencia , el dinero y el tiempo de la partida tambien el orden de los personajes.



<video controls src="imagenes/save.mp4" title="Title"></video>

![alt text](imagenes/save.png)

![alt text](imagenes/save2.png)

### Schema de la base de datos 

![alt text](imagenes/database.png)

# NPCs

En el mapa hay un total de 5 NPCs distintos que puedas interactuar de los cuales solo me han dado tiempo de hacer 3 , La posada para que los personajes puedan recuperarse, la tienda para comprar los dos objetos que hay en el juego y el ultimo es el que nos llevara a los distintos niveles para combatir 


## Inkeeper

Este es el npc que recuperara a nuestros personajes se encuentra en la casa de la parte superior del mapa