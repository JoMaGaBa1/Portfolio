<?php

namespace Tests\Unit;

use App\Models\User;
use PHPUnit\Framework\TestCase;

class AdminUserTest extends TestCase
{
    public function testIsNotAdmin()
    {
        $user = new User();
        $this->assertTrue(!$user->isAdmin());
    }

    public function testIsAdmin()
    {
        $user = new User();
        $user->admin = 1;
        $this->assertTrue($user->isAdmin());
    }
}
