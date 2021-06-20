<?php

namespace App\Http\Resources;

use App\Models\Categoria;
use App\Models\Equipo;
use App\Models\Estado;
use App\Models\Incidencia;
use Illuminate\Http\Resources\Json\JsonResource;

class IncidenciaResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'id' => $this->id,
            'titulo' => $this->titulo,
            'ubicacion' => $this->ubicacion,
            'archivo' => $this->archivo,
            'usuarios' => UserResource::collection(Incidencia::findOrFail($this->id)->users()->get()),
            'categoria' => new CategoriaResource(Categoria::findOrFail($this->categoria_id)),
            'equipo' => new EquipoResource(Equipo::findOrFail($this->equipo_id)),
            'estado' => new EstadoResource(Estado::findOrFail($this->estado_id)),
            'comentarios' => ComentarioResource::collection(Incidencia::findOrFail($this->id)->comentarios()->get()),
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
