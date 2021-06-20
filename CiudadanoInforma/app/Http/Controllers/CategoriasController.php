<?php

namespace App\Http\Controllers;

use App\Models\Categoria;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class CategoriasController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function index(Request $request) //prepara la vista con los datos correspondientes
    {
        $id = $request->query('id');
        $titulo = 'Categorías';
        $tituloTarjeta = 'Categorías registradas';
        $elementos = Categoria::all();
        $url = 'categorias';
        $create = 'categoría';
        return view('basic')
            ->with('titulo', $titulo)
            ->with('tituloTarjeta', $tituloTarjeta)
            ->with('elementos', $elementos)
            ->with('url', $url)
            ->with('create', $create)
            ->with('id', $id);
    }

    public function view($id = -1) //revisa si trae un id para saber si esta modificando o creando
    {
        if ($id === -1) {
            $prev = 'Nombre de la nueva categoría';
            $tipo = 1;
            $tituloTarjeta = 'Nueva categoría';
        } else { //si esta modificando busca los datos existentes para mostrarlos
            $prev = Categoria::findOrFail($id)->nombre;
            $tipo = 2;
            $tituloTarjeta = 'Categoría #' . $id;
        }
        $titulo = 'Categorías';
        $url = 'categorias';
        return view('basicInput')
            ->with('tipo', $tipo)
            ->with('id', $id)
            ->with('titulo', $titulo)
            ->with('tituloTarjeta', $tituloTarjeta)
            ->with('prev', $prev)
            ->with('url', $url);
    }

    public function insert(Request $request) //inserta nueva categoria
    {
        $id = DB::table('categorias')->insertGetId([
            'nombre' => $request->nombre,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        return redirect('/categorias?id=' . $id . '#' . $id);
    }

    public function update(Request $request) //modifica categoria existente
    {
        $categoria = Categoria::findOrFail($request->id);
        $categoria->nombre = $request->nombre;
        $categoria->save();
        return redirect('/categorias?id=' . $request->id . '#' . $request->id);
    }

    public function delete($id) //elimina categoria
    {
        Categoria::destroy($id);
        return redirect('/categorias');
    }
}
