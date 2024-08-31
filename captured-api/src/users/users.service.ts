import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { UsersEntity } from './users.entity';
import { userDto } from 'src/interfaces/users.interface';

@Injectable()
export class UsersService {
    constructor(
        @InjectRepository(UsersEntity)
        private userRepository: Repository<UsersEntity>,
    ){}
    
    async createUser(user:userDto): Promise<any>{
        let item = new UsersEntity();
        item.usr_nombres = user.nombre;
        item.usr_apellidos = user.apellido;
        item.usr_direccion = user.direccion;
        item.usr_identificacion = user.direccion;
        item.usr_username = user.username;
        item.usr_password = user.password;
        item.usr_telefono = user.apellido;
        const new_user = await this.userRepository.save(item);

        return new_user
    }


    async getUserByUsername(usr_username: string): Promise<UsersEntity>{
        return this.userRepository.findOneBy({usr_username});
    }

    getAllUser(): Promise<UsersEntity[]>{
        return this.userRepository.find();
    }

    async editUser(usr_username:string, user: UsersEntity): Promise<UsersEntity>{
        let toUpdate = await this.userRepository.findOneBy({usr_username});
        let update = Object.assign(toUpdate, user);
        const userUpdated = await this.userRepository.save(toUpdate);

        return
    }

    async delateUser(id:string): Promise<void>{
        await this.userRepository.delete(id);
    }

}
