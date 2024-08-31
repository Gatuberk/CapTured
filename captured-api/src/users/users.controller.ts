import { Body, Controller, Delete, Get, Param, Post, Put } from '@nestjs/common';
import { UsersService } from './users.service';
import { UsersEntity } from './users.entity';
import { userDto } from 'src/interfaces/users.interface';

@Controller('user')
export class UsersController {
    constructor(private readonly usersService: UsersService) {}

    
        @Get()
        async getAllUsers(): Promise<UsersEntity[]>{
            return await this.usersService.getAllUser();
        }

        @Get(':username')
        async getUsers(@Param() params): Promise<UsersEntity>{
            return this.usersService.getUserByUsername(params);
        }

        @Post()
        async addUser(@Body() user:userDto):Promise<UsersEntity>{
            return await this.usersService.createUser(user);
        }

        @Put(':username')
        async editarUser(@Param() params, @Body() user: UsersEntity){
            return await this.usersService.editUser(params.username, user);
        }

        @Delete(':id')
        async deleteBand(@Param() params){
            return await this.usersService.delateUser(params.id);
        }
    
}
