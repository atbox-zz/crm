package com.abc.crm.dao;

import com.abc.crm.service.bo.ClientBo;
import com.abc.crm.entity.Client;
import com.abc.crm.repo.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientDao {

    private final ClientRepo clientRepo;

    public Optional<ClientBo> getOne(Long id) {
        return clientRepo.findById(id)
                .map(ClientBo::valueOf);
    }

    public List<ClientBo> getByCompanyId(Long companyId) {
        return clientRepo.findByCompanyId(companyId).stream()
                .map(ClientBo::valueOf)
                .collect(Collectors.toList());
    }

    public Optional<Long> addOne(ClientBo clientBo) {
        Client client = Client.valueOf(clientBo);
        client = clientRepo.save(client);
        return Optional.ofNullable(client.getId());
    }

    public List<Long> addMany(List<ClientBo> clientBoList) {
        List<Client> clientList = clientBoList.stream()
                .map(Client::valueOf)
                .collect(Collectors.toList());

        clientList = clientRepo.saveAll(clientList);
        return clientList.stream()
                .map(Client::getId)
                .collect(Collectors.toList());
    }

    public Optional<Long> updateOne(ClientBo clientBo) {
        return clientRepo.findById(clientBo.getId())
                .map(e -> {
                    e.setCompanyId(clientBo.getCompanyId());
                    e.setName(clientBo.getName());
                    e.setEmail(clientBo.getEmail());
                    e.setPhone(clientBo.getPhone());
                    e.setUpdatedBy(clientBo.getUpdatedBy());
                    e.setUpdatedAt(clientBo.getUpdatedAt());
                    return e;
                }).map(clientRepo::save)
                .map(Client::getId);
    }

    public boolean deleteOne(Long id) {
        clientRepo.deleteById(id);
        return true;
    }
}
