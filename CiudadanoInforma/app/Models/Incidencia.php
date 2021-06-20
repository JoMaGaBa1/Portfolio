<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Incidencia extends Model
{
    use HasFactory;

    protected $fillable = [
        'titulo',
        'ubicacion',
        'archivo'
    ];

    public function categoria()
    {
        return $this->belongsTo(Categoria::class);
    }

    public function comentarios()
    {
        return $this->hasMany(Comentario::class);
    }

    public function equipo()
    {
        return $this->belongsTo(Equipo::class);
    }

    public function estado()
    {
        return $this->belongsTo(Estado::class);
    }

    public function users()
    {
        return $this->belongsToMany(User::class);
    }
}
