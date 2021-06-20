<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AdminUser
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next) //revisa si el usuario es administrador
    {
        if (!Auth::user()->isAdmin()) { //si no es administrador, devuelve 403
            return abort(403);
        }
        return $next($request);
    }
}
