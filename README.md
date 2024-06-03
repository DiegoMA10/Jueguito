# Jueguito
Proyecto DAM

La idea es hacer un juego en 2D en el cual tendrá una zona principal que será un pueblo y luego un escenario para las batallas.

En el mapa podré interactuar con distintas cosas como, por ejemplo, NPCs, curar a los personajes, entrar en casas, etc.

En la batalla podrás elegir las acciones que quieres hacer como magias, atacar, o utilizar objetos. (La idea es que en las batallas sea un equipo de 3).

Link al repositorio de GitHub: [https://github.com/DiegoMA10/Jueguito/tree/main/demo](https://github.com/DiegoMA10/Jueguito/tree/main/demo)

# Pantalla de inicio

Cuando ejecutamos la aplicación, aparece la pantalla del título donde podemos comenzar una partida nueva o cargar una ya existente, también podremos salir de la aplicación.

![alt text](imagenes/titleScreen.png)

## Empezar partida

Cuando comenzamos partida o cargamos partida, comenzaremos en un punto del mapa principal. Para resumir cómo dibujo el mapa, básicamente es una matriz de números donde cada número tiene una imagen asociada y ese número dibuja una imagen u otra. Cada cuadrado rojo es un mosaico o una baldosa que puede tener colisión o no.

![alt text](imagenes/mapa.png)

También podremos cambiar de mapa cuando pasemos por una puerta de cualquier casa del pueblo. Básicamente, el juego detecta si la hitbox del personaje toca el punto del centro de cada baldosa, entonces yo puedo decirle que en x baldosa haga un evento, en este caso es una transición al mapa interior. Según por qué puerta entre, el evento teletransportará al personaje a un sitio u otro.

<video controls src="imagenes/transicionMapaInt.mp4" title="Title"></video>

- El mapa interior:

![alt text](imagenes/mapaInterior.png)

## Menú del juego

En cualquier parte del mapa, podemos presionar la tecla ESC y nos llevará al menú de personajes. En el menú podemos consultar la vida de los personajes, el nivel, el tiempo que hemos de la partida, el dinero que tenemos (G), y también podremos acceder a submenús como items, stats, order y save (los únicos operativos). También está exit que te llevará a la pantalla de inicio.

![alt text](imagenes/menu.png)

## Items

Dentro de Items tendremos básicamente el inventario con todos nuestros objetos con su cantidad y si tenemos el cursor encima, nos aparecerá una pequeña descripción del objeto. También, si pulsamos ENTER en el objeto con el cursor, nos llevará a otra vista para poder elegir con qué personaje queremos consumir el objeto.

![alt text](imagenes/items.png)

<video controls src="imagenes/itemsUse.mp4" title="Title"></video>

## Stats

Cuando pulsemos, podremos elegir cuál de los personajes queremos inspeccionar y cuando elijamos cualquiera de ellos, nos llevará a su ventana de estado donde podremos ver básicamente todas sus estadísticas, el nivel, la experiencia que tiene y la que necesita para subir de nivel.

![alt text](imagenes/stats.png)

## Order

También tenemos el menú de order. Esto no influye nada en la jugabilidad, pero básicamente es para que en el mapa principal puedas manejar un personaje u otro.

<video controls src="imagenes/order.mp4" title="Title"></video>

## Save

Para el save utilizamos una base de datos para poder guardar las partidas del juego. Podemos guardar un total de 3 partidas distintas. Una vez llenos los 3 espacios de guardado, solo se podrá sobrescribir en esos mismos espacios, por lo que solo se puede tener 3 partidas distintas.

Cada partida guardará el estado de nuestros personajes y inventario como el nivel, la vida, la MP, su experiencia, el dinero y el tiempo de la partida, el orden de los personajes y la cantidad de objetos.

<video controls src="imagenes/save.mp4" title="Title"></video>

![alt text](imagenes/save.png)

![alt text](imagenes/save2.png)

### Esquema de la base de datos

![alt text](imagenes/database.png)

## Cargar partida

Para cargar una partida solo podremos hacerlo desde la pantalla de título en la segunda opción que pone "Load Game". Cuando pulsemos, habrá una vista prácticamente igual que en el save, pero en vez de guardar pondrá cargar. En load game solo podremos cargar la partida, no podremos ni sobrescribir ni guardar ninguna nueva.

![alt text](imagenes/cargar.png)

![alt text](imagenes/cargar2.png)

## NPCs

En el mapa hay un total de 5 NPCs distintos con los que puedas interactuar, de los cuales solo me han dado tiempo de hacer 3: la posada para que los personajes puedan recuperarse, la tienda para comprar los dos objetos que hay en el juego, y el último es el que nos llevará a los distintos niveles para combatir. Para interactuar con cualquiera de ellos, simplemente tendremos que acercarnos y darle a la tecla ENTER.

### Inkeeper

Este es el NPC que recuperará a nuestros personajes. Se encuentra en la casa de la parte superior del mapa.

<video controls src="imagenes/innkeper.mp4" title="Title"></video>

### Tienda de objetos

El NPC que se encuentra en la casa inferior derecha del mapa es con el que podremos interactuar y comprar los objetos que hay en el juego. También podrás vender los objetos (aunque no valga prácticamente para nada). El jugador venderá los objetos a la mitad de su precio original.

![alt text](imagenes/tienda1.png)

![alt text](imagenes/tienda2.png)

![alt text](imagenes/tienda3.png)

![alt text](imagenes/tienda4.png)

La venta es prácticamente igual a las anteriores vistas con unas pequeñas modificaciones.

### Guardián

Por último, este NPC es el que te llevará a los combates. Es básicamente como en la posada, pero en vez de un fundido a negro, te pregunta a qué nivel quieres ir.

![alt text](imagenes/guardian.png)

![alt text](imagenes/guardian2.png)

## Combate

El combate es prácticamente parecido a un combate por turnos, pero en este cada entidad del combate tiene como una barra que se va rellenando y cuando esta está llena, si la entidad es un enemigo, está lista para realizar su acción. En el caso de los personajes que maneja el jugador, estarán listos para poder elegir la acción que quieren realizar, ya sea atacar, utilizar magias o items y a qué enemigo o aliado lanzar esa acción.

Vista cuando comenzamos el combate:

![alt text](imagenes/combate1.png)

Cuando un personaje esté listo para realizar una acción, se te indicará a qué personaje estás manejando con una flechita encima del personaje. Te saldrá un menú con 3 opciones: attack, magic y items.

![alt text](imagenes/combate2.png)

Cuando pulses en attack, podrás seleccionar a qué enemigo atacar.

![alt text](imagenes/combateCursor.png)

Con las magias, te saldrán todas tus magias para elegir qué magia quieres utilizar y como en el ataque, a qué enemigo atacar.

![alt text](imagenes/combateMagia.png)

Por último, los items. Aquí te saldrán todos los objetos que tengas. Una vez selecciones cualquier item, podrás elegir a qué personaje darle el objeto.

![alt text](imagenes/combateItem.png)

Cualquiera de estas tres opciones, el personaje realizará la acción y una vez finalizada, el personaje volverá a su posición y se le vaciará la barra de acción (ATB) pasando así al siguiente turno.

En el caso de que ganes la batalla, los personajes lo celebrarán y te aparecerán mensajes con la experiencia recibida y el dinero conseguido. También en caso de que los personajes suban de nivel, te lo notificarán de igual manera.

![alt text](imagenes/combateWin.png)

Por último, el Game Over. Si en el combate todos tus personajes caen derrotados, como las notificaciones de cuando ganas el combate, se te notificará que has perdido mientras cambia la música y se va haciendo un fundido a negro poco a poco hasta que te salte la pantalla del título, perdiendo así todo el progreso en caso de que no hayas guardado.