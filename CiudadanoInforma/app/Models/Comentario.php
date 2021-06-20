<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Comentario extends Model
{
    use HasFactory;

    protected $fillable = [
        'contenido'
    ];

    public function incidencia()
    {
        return $this->belongsTo(Incidencia::class);
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
