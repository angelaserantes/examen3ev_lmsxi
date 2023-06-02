# Examen 3ª evaluación

## XQUERY

1. Devuelve la frase "[nombre] ha ganado el premio de [categoria] en el año [año]":

```
for $premio in doc("premios.xml")//premio
return concat($premio/premiado, " ha ganado el premio de ", $premio/@categoria, " en el año ", $premio/año)
```
Con el bucle for iteramos sobre los diferentes elementos y con concat concantenamos el nombre, la categoría y el año junto a la cadena de texto, retornando la frase.

2. Una tabla html [categoria] | [premiado] ordenada de mayor a menor por los [años]:

```
<table>
{
  for $premio in doc("C:\Users\Angela\IdeaProjects\examen3ev_lmsxi\premios.xml")//premio
  let $categoria := $premio/@categoria
  let $premiado := $premio/premiado
  order by $premio/año descending
  return <tr><td>{$categoria}</td><td>{$premiado}</td></tr>
}
</table>
```
En esta ocasión generamos una tabla para mostrar la categoría y el premiado, ordenando los años de mayor a menor. Como en la anterior empleamos el bucle for para iterar, se asignan las variables (let $categoria := $premio/@categoria
let $premiado := $premio/premiado), ordenamos con order by y se devuelven los valores de la tabla.


3. Incluir un nuevo premiado en un nuevo fichero:

```
let $nuevoPremio := <premio categoria="matematicas">
                       <año>2023</año>
                       <premiado>Luis Caffareli</premiado>
                   </premio>

let $premioNobelXML := <premio_nobel>
                            <premios>
                                {doc("C:\Users\Angela\IdeaProjects\examen3ev_lmsxi\premios.xml")//premio}
                                {$nuevoPremio}
                            </premios>
                        </premio_nobel>

return $premioNobelXML
```
En este caso, creamos un nuevo premio asociado a una nueva categoría "matematicas" y al premiado y el año. Después creamos un nuevo XML (premionobel) que añade el nuevoPremio manteniendo los que ya había. 

4. Realizar un fichero nuevo incluyendo motivos en los que no tienen:

- Manteniendo los premiados que ya había:
```
let $nuevoXML :=
  <premios_nobel>
    <premios>
      {
        for $premio in doc("C:\Users\Angela\IdeaProjects\examen3ev_lmsxi\premios.xml")//premio
        return
          let $premiado := $premio/premiado
          let $categoria := $premio/@categoria
          let $año := $premio/año
          return
            if ($premiado = "Isaac Bashevis Singer")
            then
              <premio categoria="{$categoria}">
                <año>{$año}</año>
                <premiado>{$premiado}</premiado>
                <motivo>Por su apasionado arte narrativo</motivo>
              </premio>
            else if ($premiado = "Mario Vargas Llosa")
            then
              <premio categoria="{$categoria}">
                <año>{$año}</año>
                <premiado>{$premiado}</premiado>
                <motivo>Por la ciudad y los perros</motivo>
              </premio>
            else
              $premio
      }
    </premios>
  </premios_nobel>

return $nuevoXML
```

Con esta consulta, mantenemos los premiados con motivo que ya había, pero además, si coincide con los nombres de los premiados que declaramos en el if y en else if se añade la categoría, el año, el premiado y el motivo retornándolo en el nuevo archivo.

- Sólo con los premiados que no tenian motivo:

```
let $nuevoXML :=
  <premios_nobel>
    <premios>
      {
        for $premio in doc("C:\Users\Angela\IdeaProjects\examen3ev_lmsxi\premios.xml")//premio
        return
          if (empty($premio/motivo))
          then
            let $premiado := $premio/premiado
            let $categoria := $premio/@categoria
            let $año := $premio/año
            return
              if ($premiado = "Isaac Bashevis Singer")
              then
                <premio categoria="{$categoria}">
                  <año>{$año}</año>
                  <premiado>{$premiado}</premiado>
                  <motivo>Por su apasionado arte narrativo</motivo>
                </premio>
              else if ($premiado = "Mario Vargas Llosa")
              then
                <premio categoria="{$categoria}">
                  <año>{$año}</año>
                  <premiado>{$premiado}</premiado>
                  <motivo>Por la ciudad y los perros</motivo>
                </premio>
          else $premio
      }
    </premios>
  </premios_nobel>

return $nuevoXML
```
Empleando la misma estructura que en la anterior consulta en este caso sólo devolvemos los premiados que no tenían motivo con un nuevo motivo.