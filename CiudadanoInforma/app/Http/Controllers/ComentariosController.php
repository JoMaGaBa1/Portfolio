<?php

namespace App\Http\Controllers;

use App\Models\Comentario;

class ComentariosController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function index() //prepara la vista con los datos correspondientes
    {
        $comentarios = Comentario::all();
        return view('comentarios')->with('comentarios', $comentarios);
    }

    public function delete($id) //elimina un comentario
    {
        Comentario::destroy($id);
        return back();
    }
}
