<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\IncidenciaResource;
use App\Models\Incidencia;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Throwable;

class IncidenciasController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:sanctum');
    }

    public function index(Request $request) //devuelve incidencias
    {
        try {
            $categoria = $request->query('categoria');
            if ($categoria === null) { //si no pide categoria, devuelve todas las incidencias
                return response()->json(IncidenciaResource::collection(DB::table('incidencias')->get()));
            } //si pide categoria, devuelve las incidencias de esa categoria concreta
            $id = DB::table('categorias')->where('nombre', $categoria)->first()->id;
            $incidencias = DB::table('incidencias')->where('categoria_id', $id)->get();
            return response()->json(IncidenciaResource::collection($incidencias));
        } catch (Throwable $ex) {
            return response()->json(null);
        }
    }

    public function extractWithId($id) //devuelve una incidencia concreta
    {
        return response()->json(new IncidenciaResource(Incidencia::findOrFail($id)));
    }

    public function insert(Request $request) //inserta una nueva incidencia y la devuelve
    {
        $user_id = $request->user()->id;
        try {
            $filename = null;
            if ($request->file('archivo') !== null) {
                $paths = [];
                $archivo = $request->file('archivo');
                $extension = $archivo->getClientOriginalExtension();
                $filename = 'archivo' . time() . '.' . $extension;
                $paths[] = $archivo->storeAs('public', $filename);
            }
            $id = DB::table('incidencias')->insertGetId([
                'titulo' => $request->titulo,
                'ubicacion' => $request->ubicacion,
                'archivo' => $filename,
                'categoria_id' => $request->categoria_id,
                'equipo_id' => $request->equipo_id,
                'estado_id' => $request->estado_id,
                'created_at' => Carbon::now()->toDateTimeString(),
                'updated_at' => Carbon::now()->toDateTimeString()
            ]);
            DB::table('comentarios')->insert([
                'contenido' => $request->comentario,
                'incidencia_id' => $id,
                'user_id' => $user_id,
                'created_at' => Carbon::now()->toDateTimeString(),
                'updated_at' => Carbon::now()->toDateTimeString()
            ]);
            DB::table('incidencia_user')->insert([
                'incidencia_id' => $id,
                'user_id' => $user_id,
                'created_at' => Carbon::now()->toDateTimeString(),
                'updated_at' => Carbon::now()->toDateTimeString()
            ]);
            return response()->json(new IncidenciaResource(Incidencia::findOrFail($id)));
        } catch (Throwable $ex) {
            return $ex;
        }
    }

    public function comment($id, Request $request) { //comenta sobre una incidencia y devuelve la incidencia comentada completa
        if (strlen($request->comentario) > 0) {
            $user_id = $request->user()->id;
            $existe = false;
            try {
                $inc = Incidencia::findOrFail($id);
                foreach ($inc->users()->get() as $user) {
                    if ($user->id == $user_id) {
                        $existe = true;
                        break;
                    } else {
                        $existe = false;
                    }
                }
                if (!$existe) { //si el usuario no ha comentado antes, se registra como participante
                    DB::table('incidencia_user')->insert([
                        'incidencia_id' => $id,
                        'user_id' => $user_id,
                        'created_at' => Carbon::now()->toDateTimeString(),
                        'updated_at' => Carbon::now()->toDateTimeString()
                    ]);
                }
                DB::table('comentarios')->insert([
                    'contenido' => $request->comentario,
                    'incidencia_id' => $id,
                    'user_id' => $user_id,
                    'created_at' => Carbon::now()->toDateTimeString(),
                    'updated_at' => Carbon::now()->toDateTimeString()
                ]);
            } catch (Throwable $ex) {
                return $ex;
            }
        }
        return response()->json(new IncidenciaResource(Incidencia::findOrFail($id)));
    }
}
