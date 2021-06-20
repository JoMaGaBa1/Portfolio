<?php

namespace App\Http\Resources;

use App\Models\Incidencia;
use App\Models\User;
use Illuminate\Http\Resources\Json\JsonResource;

class ComentarioResource extends JsonResource
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
            'contenido' => $this->contenido,
            'incidencia' => Incidencia::findOrFail($this->incidencia_id)->titulo,
            'usuario' => User::findOrFail($this->user_id)->name,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
